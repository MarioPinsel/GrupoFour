#!/bin/bash
echo "Content-type: text/html"
echo ""

# Carpeta temporal donde guardar imagen
UPLOAD_DIR="/tmp/uploads"
mkdir -p "$UPLOAD_DIR"

# Leer datos de POST
read -n "$CONTENT_LENGTH" POST_DATA

# Extraer el campo "name" (texto)
NAME=$(echo "$POST_DATA" | sed -n 's/.*name=\([^&]*\).*/\1/p' | sed 's/+/ /g')

# Extraer la ruta del archivo subido
FILE_TMP="$UPLOAD_DIR/uploaded_file"
echo "$POST_DATA" | sed -n 's/.*filename="\([^"]*\)".*/\1/p' > "$FILE_TMP"

# Llamar a tu codificador
BASE64=$(python3 /usr/lib/cgi-bin/codi.py "$FILE_TMP")

# Calcular sha256
SHA256=$(sha256sum "$FILE_TMP" | awk '{print $1}')

# Guardar en SQLite
sqlite3 /var/www/html/imagenes.db "INSERT INTO image (name, imagen64, sha256) VALUES ('$NAME', '$BASE64', '$SHA256');"

# Respuesta HTML
echo "<html><body>"
echo "<h2>Imagen subida correctamente</h2>"
echo "<p>Nombre: $NAME</p>"
echo "<p>SHA256: $SHA256</p>"
echo "</body></html>"
