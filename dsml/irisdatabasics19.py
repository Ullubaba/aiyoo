import pandas as pd

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/IRIS.csv'
data = pd.read_csv(file_path)

df = pd.DataFrame(data)

grouped = df.groupby('species')

stats = grouped.describe().T

for species,group in grouped:
    print(f"Basic statistics for {species} :")
    print(group.describe())
    print("\n")
    
output_file = "IRIS_Species_Stats.csv"
stats.to_csv(output_file)
print("Data sent to", output_file)