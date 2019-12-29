#Importamos librerías
import pandas as pd
import numpy as np
import pickle
#Leemos el dataset
datos=pd.read_csv("diabetes.csv")
datos=datos.replace(np.nan, "0") #Si hay algun campo vacio se transforma a 0
#Entradas
X=datos[['Embarazos', 'Glucosa', 'PresionSanguinea', 'PliegueCutaneo', 'Insulina', 'IndiceDeMasaCorporal', 
         'PedigriDiabetesFuncion' ,'Edad']]
#Salidas
y=datos['DiabetesResultado']
#Datos a entrenar y probar
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y)
#Escalamos las entradas
from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
scaler.fit(X_train)
X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)
#Importamos el clasificador y entrenamos la red neuronal con sus atributos
from sklearn.neural_network import MLPClassifier
mlp=MLPClassifier(hidden_layer_sizes=(10,10,10), max_iter=10000, alpha=0.0001,
                     solver='adam', random_state=21,tol=0.000000001)
#mlp = MLPClassifier(hidden_layer_sizes=(6,6,6,6),solver='lbfgs',max_iter=6000)
mlp.fit(X_train,y_train)
predictions=mlp.predict(X_test)
#Imprimimos el reporte de claisficación
from sklearn.metrics import classification_report
print(classification_report(y_test,predictions))
#Imprimimos el error y la precisión del modelo
from sklearn.metrics import accuracy_score
Error = 1-accuracy_score(y_test, predictions)
Precision = mlp.score(X_test, y_test)
print('Error: ')
print(Error)

print('Precision: ')
print(Precision)
#Guardamos el error y la precision para futuras referencias
txt = open("precision y error del modelo.txt", "w+");
txt.write(str(Precision)+"\n")
txt.write(str(Error))
txt.close()
#Guadamos el modelo
file = 'diabetes_modelo.sav'
pickle.dump(mlp, open(file, 'wb'))
#Guardamos el scaler que utilizamos para usarlo en el otro script
from sklearn.externals.joblib import dump, load
dump(scaler, 'scaler.bin', compress=True)