#Importamos las liberias
import sys
import pickle
#Asignamos los valores que se pasaron desde Java a los distintos campos
embarazos = sys.argv[1]
glucosa= sys.argv[2]
presion= sys.argv[3]
pliegue= sys.argv[4]
insulina= sys.argv[5]
imc= sys.argv[6]
fpd= sys.argv[7]
edad= sys.argv[8]
#Asignamos las variables para efectuar la predicción
Xnew = [[embarazos, glucosa, presion, pliegue, insulina, imc, fpd, edad]]
#Cargamos el modelo entrenado
modelo_cargado = pickle.load(open('diabetes_modelo.sav', 'rb'))
#Cargamos el scaler que se utilizó cuando se entrenó la red neuronal
from sklearn.externals.joblib import dump, load
scaler=load('scaler.bin')
#Predecimos e imprimimos la predicción
Xnew = scaler.transform(Xnew)
ynew=modelo_cargado.predict(Xnew)
print(ynew) #El resultado se guarda dentro de una variable de Java para mostrarlo en la interfaz