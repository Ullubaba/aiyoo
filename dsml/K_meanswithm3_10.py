import math

points = {
    "p1": [2, 10],
    "p2": [2, 5],
    "p3": [8, 4],
    "p4": [5, 8],
    "p5": [7, 5],
    "p6": [6, 4],
    "p7": [1, 2],
    "p8": [4, 9]
}

m1 = points["p1"]
m2 = points["p4"]
m3 = points["p7"]

def euclidean_distance(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

def compute_centroid(cluster_points):
    x_coords = [points[point][0] for point in cluster_points]
    y_coords = [points[point][1] for point in cluster_points]
    return [sum(x_coords) / len(x_coords), sum(y_coords) / len(y_coords)]

def kmeans_clustering(points, m1, m2, m3, max_iterations=100):
    prev_centroids = [m1, m2, m3]
    clusters = {"c1": [], "c2": [], "c3": []}

    for _ in range(max_iterations):
        new_clusters = {"c1": [], "c2": [], "c3": []}

        for point, coordinates in points.items():
            distance_to_m1 = euclidean_distance(coordinates, m1)
            distance_to_m2 = euclidean_distance(coordinates, m2)
            distance_to_m3 = euclidean_distance(coordinates, m3)
            
            if distance_to_m1 <= distance_to_m2 and distance_to_m1 <= distance_to_m3:
                new_clusters["c1"].append(point)
            elif distance_to_m2 <= distance_to_m1 and distance_to_m2 <= distance_to_m3:
                new_clusters["c2"].append(point)
            else:
                new_clusters["c3"].append(point)

        m1 = compute_centroid(new_clusters["c1"]) if new_clusters["c1"] else m1
        m2 = compute_centroid(new_clusters["c2"]) if new_clusters["c2"] else m2
        m3 = compute_centroid(new_clusters["c3"]) if new_clusters["c3"] else m3

        if prev_centroids == [m1, m2, m3]:
            break
        prev_centroids = [m1, m2, m3]

    return new_clusters, m1, m2, m3

clusters, new_m1, new_m2, new_m3 = kmeans_clustering(points, m1, m2, m3)

p6_cluster = "c1" if "p6" in clusters["c1"] else ("c2" if "p6" in clusters["c2"] else "c3")
population_m3 = len(clusters["c3"])

print(f"Clusters after assignments: {clusters}")
print(f"1. P6 belongs to cluster: {p6_cluster}")
print(f"2. Population of cluster around m3: {population_m3}")
print(f"3. Updated values of m1, m2, m3 are: {new_m1}, {new_m2}, {new_m3}")

        
    