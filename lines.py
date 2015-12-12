import glob

countLines = 0
files = glob.glob("*.java")
# files.remove("lines.py")

for x in range(len(files)):
	fileLine = open(files[x]).read().split("\n")
	countLines += len(fileLine)

print("Lines: %d" % countLines)