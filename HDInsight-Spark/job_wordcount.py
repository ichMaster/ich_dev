from __future__ import print_function

import sys

from pyspark import SparkContext

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: network_wordcount.py <input file> <output file>", file=sys.stderr)
        exit(-1)
    sc = SparkContext(appName="PythonWordCountJob")

    lines = sc.textFile(sys.argv[1]).repartition(1)
    counts = lines.flatMap(lambda line: line.split(" "))\
                  .map(lambda word: (word, 1))\
		  .reduceByKey(lambda a, b: a+b)

    counts.saveAsTextFile(sys.argv[2])
