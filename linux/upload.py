#!/usr/bin/python3
import sys, os, json, sqlite3, hashlib
from deco import decode_base64   # tu decodificador

# 🔹 Encabezado CGI obligatorio
print("Content-Type: text/html")
print()

try:
    # 🔹 Leer el tamaño del body
    length = int(os.environ.get("CONTENT_LENGTH", 0))
    data = sys.stdin.read(length)

    # 🔹 Parsear JSON recibido
    req = json.loads(data)
    name = req.get("name")
    imagen64 = req.get("imagen64")

    if not name or not imagen64:
        print("Error: faltan datos")
        sys.exit(0)

    # 🔹 Decodificar para calcular hash
    imagen_bytes = decode_base64(imagen64)
    sha256 = hashlib.sha256(imagen_bytes).hexdigest()

    # 🔹 Guardar en la DB
    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()
    cur.execute(
        "INSERT INTO image (name, imagen64, sha256) VALUES (?, ?, ?)",
        (name, imagen64, sha256)
    )
    conn.commit()
    conn.close()

    print(f"✅ Imagen '{name}' guardada en la base de datos (SHA256: {sha256})")

except Exception as e:
    # Para debug
    print(f"❌ Error en upload.py: {e}")
