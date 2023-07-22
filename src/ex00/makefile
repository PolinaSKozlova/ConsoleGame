rm -rf target

mkdir target

cd target ; jar -xf ../lib/JColor-5.0.0.jar com; jar -xf ../lib/jcommander-1.82.jar com ; cd ..
cp -R resources target/.
javac -cp ".:./lib/jcommander-1.82.jar:./lib/JColor-5.0.0.jar" -d ./target Game/*.java
jar -cfm target/game.jar manifest.txt -C target .

java -jar target/game.jar --enemiesCount=10 --wallsCount=10 --size=30 --profile=production