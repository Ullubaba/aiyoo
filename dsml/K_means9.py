import math

points = {
    "p1": [0.1, 0.6],
    "p2": [0.15, 0.71],
    "p3": [0.08, 0.9],
    "p4": [0.16, 0.85],
    "p5": [0.2, 0.3],
    "p6": [0.25, 0.5],
    "p7": [0.24, 0.1],
    "p8": [0.3, 0.2]
}

m1 = points["p1"]
m2 = points["p8"]

def euclidean_distance(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

clusters = {"c1": [], "c2": []}  

for point, coordinates in points.items():  
    distance_to_m1 = euclidean_distance(coordinates, m1)
    distance_to_m2 = euclidean_distance(coordinates, m2)

    if distance_to_m1 < distance_to_m2:
        clusters["c1"].append(point)
    else:
        clusters["c2"].append(point)

def compute_centroid(cluster_points):
    x_coords = [points[point][0] for point in cluster_points]
    y_coords = [points[point][1] for point in cluster_points]
    return [sum(x_coords) / len(x_coords), sum(y_coords) / len(y_coords)]

new_m1 = compute_centroid(clusters["c1"])
new_m2 = compute_centroid(clusters["c2"])

p6_cluster = "c1" if "p6" in clusters["c1"] else "c2"
population_c2 = len(clusters["c2"])

print(f"Clusters after assignments: {clusters}")
print(f"1. P6 belongs to cluster: {p6_cluster}")
print(f"2. Population of cluster around m2: {population_c2}")
print(f"3. Updated values of m1 and m2 are: {new_m1} & {new_m2}")