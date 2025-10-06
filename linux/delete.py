#!/usr/bin/python3
import sys, os, json, sqlite3

print("Content-Type: text/html")
print()

try:
    # Leer JSON del body
    length = int(os.environ.get("CONTENT_LENGTH", 0))
    data = sys.stdin.read(length)
    req = json.loads(data)

    name = req.get("name")
    if not name:
        print("❌ Error: No se recibió el nombre de la imagen a eliminar.")
        sys.exit(0)

    # Conexión a la base de datos
    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()

    # Verificar si existe
    cur.execute("SELECT id FROM image WHERE name = ?", (name,))
    row = cur.fetchone()

    if not row:
        print(f"⚠️ No existe ninguna imagen con el nombre '{name}'.")
    else:
        cur.execute("DELETE FROM image WHERE name = ?", (name,))
        conn.commit()
        print(f"🗑️ Imagen '{name}' eliminada correctamente.")

    conn.close()

except Exception as e:
    print(f"❌ Error en delete.py: {e}")
