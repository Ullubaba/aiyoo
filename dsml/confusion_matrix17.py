import numpy as np

# confusion_matrix = np.array([[1,1],
#                             [8,90]])

#to take custom input of TP,FP,FN,TN

TP = int(input("Enter value of true positive : "))
FP = int(input("Enter value of false positive : "))
FN = int(input("Enter value of false negative : "))
TN = int(input("Enter value of true negative : "))

confusion_matrix = np.array([
    [TP,FP],
    [FN,TN]
])

TP = confusion_matrix[0][0]  #true positives
FP = confusion_matrix[0][1]  #false positives
FN = confusion_matrix[1][0]  #false negatives
TN = confusion_matrix[1][1]  #true negatives

Accuracy = (TP+TN)/(TP+FP+TN+FN)

error_rate = 1 - Accuracy

Precision = TP/(TP+FP) if (TP+FP) != 0 else 0

Recall = TP/(TP+FN) if (TP+FN) != 0 else 0

print(f"Accuracy : {Accuracy}\n")
print(f"Error Rate: {error_rate}\n")
print(f"Precision: {Precision}\n")
print(f"Recall : {Recall}\n")



