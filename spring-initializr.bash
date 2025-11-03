curl https://start.spring.io/starter.zip \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.5.7 \
  -d groupId=cn.ac.panlab \
  -d artifactId=backend \
  -d name=backend \
  -d packageName=cn.ac.panlab.backend \
  -d dependencies=web \
  -o backend.zip

unzip backend.zip -d backend

rm backend.zip

# Replace \t with spaces.
find backend/* -type f -exec sed -i 's/\t/    /g' {} +
