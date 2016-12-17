#!/home/ich/anaconda2/bin/python

import sys

if __name__ != "__main__":
    sys.exit(0)

if len(sys.argv) < 3:
    sys.exit(0)

sourceName = sys.argv[1]
stringToAdd = sys.argv[2]
destinationName = sys.argv[3]

print sourceName
print destinationName
print stringToAdd

sourceFile = open(sourceName, 'r')
destinationFile = open(destinationName, 'w')

for row in sourceFile:
    destinationFile.write(row[:-1] + stringToAdd + "\n")


sys.exit(0)
