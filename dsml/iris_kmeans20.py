import numpy as np
from sklearn.datasets import load_iris
from sklearn.preprocessing import StandardScaler


data = load_iris()
X = data.data  

scaler = StandardScaler()
X = scaler.fit_transform(X)

K = 3 
n_iterations = 10

np.random.seed(42)
initial_indices = np.random.choice(X.shape[0], K, replace=False)
centroids = X[initial_indices]

def euclidean_distance(a, b):
    return np.linalg.norm(a - b)

for iteration in range(n_iterations):
    clusters = [[] for _ in range(K)]
    for point in X:
        distances = [euclidean_distance(point, centroid) for centroid in centroids]
        cluster_idx = np.argmin(distances)
        clusters[cluster_idx].append(point)

    new_centroids = []
    for cluster in clusters:
        if cluster:  
            new_centroids.append(np.mean(cluster, axis=0))
        else:  
            new_centroids.append(centroids[len(new_centroids)])

    centroids = np.array(new_centroids)

print("Final cluster means (centroids):")
for i, centroid in enumerate(centroids):
    print(f"Cluster {i + 1}: {centroid}")
