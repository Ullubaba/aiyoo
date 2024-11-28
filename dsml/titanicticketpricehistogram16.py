import pandas as pd
import matplotlib.pyplot as plt

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Titanic.csv'
data = pd.read_csv(file_path)

print(data.head())

plt.figure(figsize=(10,6))
plt.hist(data['Fare'],bins=30,color='skyblue',edgecolor='black')
plt.title("Distribution by Ticket prices (Fare)" , fontsize=16)
plt.xlabel("Fare", fontsize=12)
plt.ylabel("Frequency", fontsize=12)
plt.grid(axis='y',linestyle ='--',alpha=0.7)
plt.show()