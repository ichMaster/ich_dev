#!C:\Python34\python.exe
"""Generate file with GPS points

"""
import random
import time


def get_random(max):
    random.seed(time.time())
    r = random.uniform(0, max)
    return r


def get_next_point(point, distance):
    next_point = []

    for i in point:
        next_point.append(i + get_random(distance))
    return next_point


def generate():
    point = [1, 2, 3]
    next_point = get_next_point(point, 5)
    print(next_point)
    return


def main():
    generate()
    return


# CLI execution
if __name__ == "__main__":
    main()
