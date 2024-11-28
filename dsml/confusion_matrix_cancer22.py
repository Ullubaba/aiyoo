# Confusion matrix values
TP = 90   # True Positives
TN = 9560 # True Negatives
FP = 140  # False Positives
FN = 210  # False Negatives
Total = 10000  # Total number of samples

accuracy = (TP + TN) / Total
error_rate = 1 - accuracy
precision = TP / (TP + FP)
recall = TP / (TP + FN)

print("Confusion Matrix Metrics:")
print(f"Accuracy: {accuracy * 100:.2f}%")
print(f"Error Rate: {error_rate * 100:.2f}%")
print(f"Precision: {precision * 100:.2f}%")
print(f"Recall: {recall * 100:.2f}%")
