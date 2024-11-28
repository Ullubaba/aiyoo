import pandas as pd
import math

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Lipstick.csv'
lipstick_data = pd.read_csv(file_path)

def calculate_entropy(column):
    total = len(column)
    counts = column.value_counts()
    entropy = 0
    
    for count in counts:
        probability = count/total
        entropy = entropy - probability*math.log2(probability)
        
    return entropy

def split_data(data,attribute,value):
    return data[data[attribute] == value]

def calculate_information_gain(data,attribute,target):
    total_entropy = calculate_entropy(data[target])
    values = data[attribute].unique()
    weighted_entropy = 0
    
    for value in values:
        subset = split_data(data,attribute,value)
        weighted_entropy = weighted_entropy + (len(subset)/len(data))*calculate_entropy(subset[target])
        
    information_gain = total_entropy - weighted_entropy
    return information_gain

target_entropy = calculate_entropy(lipstick_data["Buys"])
print(f"Entropy of 'Buys': {target_entropy:.4f}")

attributes = {"Age","Income","Gender","Ms"}
info_gains = {attr:calculate_information_gain(lipstick_data,attr,"Buys") for attr in attributes}

root_node = max(info_gains,key=info_gains.get)
print("\nInformation gain for each attribute:")
for attr,ig in info_gains.items():
    print(f"{attr}:{ig:.4f}")
    
print("\nroot Node:", root_node)

        