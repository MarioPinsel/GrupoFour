#!/usr/bin/python3
import sys, os, json, sqlite3

print("Content-Type: text/html")
print()

try:
    length = int(os.environ.get("CONTENT_LENGTH", 0))
    data = sys.stdin.read(length)
    req = json.loads(data)

    currentName = req.get("currentName")
    newName = req.get("newName")

    if not currentName or not newName:
        print("❌ Faltan datos para renombrar")
        sys.exit(0)

    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()

    # Verificar si existe el nombre actual
    cur.execute("SELECT id FROM image WHERE name = ?", (currentName,))
    row = cur.fetchone()
    if not row:
        print(f"⚠️ No existe ninguna imagen con el nombre '{currentName}'.")
    else:
        # Actualizar el nombre
        cur.execute("UPDATE image SET name=? WHERE name=?", (newName, currentName))
        conn.commit()
        print(f"✅ Imagen renombrada: '{currentName}' → '{newName}'")

    conn.close()

except Exception as e:
    print(f"❌ Error en rename.py: {e}")
