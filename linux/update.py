#!/usr/bin/python3
import sys, os, json, sqlite3, hashlib
from deco import decode_base64

print("Content-Type: text/html\n")

try:
    length = int(os.environ.get("CONTENT_LENGTH", 0))
    data = sys.stdin.read(length)
    req = json.loads(data)

    name = req.get("name")
    imagen64 = req.get("imagen64")

    if not name or not imagen64:
        print("❌ Faltan datos para actualizar")
        sys.exit(0)

    imagen_bytes = decode_base64(imagen64)
    sha256 = hashlib.sha256(imagen_bytes).hexdigest()

    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()

    # Verificar si existe
    cur.execute("SELECT id FROM image WHERE name = ?", (name,))
    row = cur.fetchone()
    if not row:
        print(f"⚠️ No existe ninguna imagen con el nombre '{name}'. No se puede actualizar.")
    else:
        cur.execute("UPDATE image SET imagen64=?, sha256=? WHERE name=?", (imagen64, sha256, name))
        conn.commit()
        print(f"✅ Imagen '{name}' actualizada correctamente (SHA256: {sha256})")

    conn.close()

except Exception as e:
    print(f"❌ Error en update.py: {e}")
