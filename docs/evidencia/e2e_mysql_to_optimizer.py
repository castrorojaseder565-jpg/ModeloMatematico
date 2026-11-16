"""Flujo equivalente a OptimizationService.optimize(): MySQL -> JSON (camelCase) -> FastAPI -> resultado.
Reproduce lo que hace Spring Boot (findAll + RestClient POST), y verifica el óptimo por fuerza bruta."""
import itertools, json, urllib.request, pymysql

conn = pymysql.connect(unix_socket="/var/run/mysqld/mysqld.sock", user="root", database="eduoptima_db", charset="utf8mb4")
cur = conn.cursor(pymysql.cursors.DictCursor)
cur.execute("SELECT id, name, topic, academic_risk AS academicRisk, priority FROM student ORDER BY id")
students = cur.fetchall()
cur.execute("SELECT id, name, specialty, max_capacity AS maxCapacity FROM tutor ORDER BY id")
tutors = cur.fetchall()
print(f"[MySQL] estudiantes={len(students)}  tutores={len(tutors)}")

payload = json.dumps({"students": students, "tutors": tutors}).encode()
req = urllib.request.Request("http://127.0.0.1:8000/optimize", payload, {"Content-Type": "application/json"})
resp = json.load(urllib.request.urlopen(req))
json.dump(resp, open("/home/claude/evidence/seed_response.json", "w"), ensure_ascii=False, indent=2)

print(f"[FastAPI] status={resp['status']}  objectiveValue={resp['objectiveValue']}")
print(f"{'Estudiante':<14}{'Tutor':<16}{'Área':<14}{'Score':>5}")
for a in sorted(resp["assignments"], key=lambda a: -a["score"]):
    print(f"{a['studentName']:<14}{a['tutorName']:<16}{a['topic']:<14}{a['score']:>5}")
asignados = {a["studentId"] for a in resp["assignments"]}
print("Sin asignar:", [s["name"] for s in students if s["id"] not in asignados])

# ── Verificación independiente ──
# 1) Restricciones R1-R3
cap = {t["id"]: t["maxCapacity"] for t in tutors}
from collections import Counter
carga = Counter(a["tutorId"] for a in resp["assignments"])
assert all(carga[t] <= cap[t] for t in carga), "R2 violada"
assert len(asignados) == len(resp["assignments"]), "R1 violada"
tema = {s["id"]: s["topic"].lower() for s in students}; esp = {t["id"]: t["specialty"].lower() for t in tutors}
assert all(tema[a["studentId"]] == esp[a["tutorId"]] for a in resp["assignments"]), "R3 violada"
print("[Verif] R1, R2 y R3 cumplidas")

# 2) Óptimo por fuerza bruta: cada estudiante -> un tutor compatible o ninguno
opciones = [[None] + [t["id"] for t in tutors if t["specialty"].lower() == s["topic"].lower()] for s in students]
mejor = 0
for combo in itertools.product(*opciones):
    c = Counter(x for x in combo if x is not None)
    if any(c[t] > cap[t] for t in c): continue
    z = sum(s["priority"] * s["academicRisk"] for s, x in zip(students, combo) if x is not None)
    mejor = max(mejor, z)
print(f"[Verif] óptimo por fuerza bruta = {mejor}  |  CBC = {resp['objectiveValue']}  ->  {'COINCIDEN' if mejor == resp['objectiveValue'] else 'DIFIEREN'}")
