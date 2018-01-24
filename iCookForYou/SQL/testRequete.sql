
#Liste des aliments avec leurs quantités dans le stock de l'user 1
SELECT U.name, U.firstname, F.idFood, F.name, F.quantity, F.unit, S.quantityS
FROM TUser AS U, TFood AS F, TStock AS S
WHERE U.idUser = '1'
AND U.idUser = S.idUserS
AND S.idFood = F.idFood;

#Quantité totale d'un aliment X pour l'utilisateur 1
SELECT U.name, U.firstname, F.name, (F.quantity*S.quantityS) AS QuantityTotal, F.unit
FROM TUser AS U, TFood AS F, TStock AS S
WHERE U.idUser = '1'
AND S.idFood = F.idFood
AND F.name = "lait";

#Quantité totale des aliments dans le stock de l'utilisateur 1
SELECT U.name, U.firstname, F.name, (F.quantity*S.quantityS) AS QuantityTotal, F.unit
FROM TUser AS U, TFood AS F, TStock AS S
WHERE U.idUser = '1'
AND U.idUser = S.idUserS
AND S.idFood = F.idFood;

#Afficher l'historique des recettes de l'user 1
SELECT U.name, U.firstname, H.idUserH, H.idRecipeH
FROM TUser AS U, THistory AS H
WHERE U.idUser = '1'
AND U.idUser = H.idUserH;

#Compter le nombre de recettes réalisé par l'user 1
SELECT U.name, U.firstname, COUNT(idRecipeH) AS nbRecettes 
FROM TUser AS U, THistory AS H
WHERE U.idUser = '1'
AND U.idUser = H.idUserH;

#Afficher les recettes favorites de l'user 1
SELECT U.name, U.firstname, F.idUserF, F.idRecipeF
FROM TUser AS U, TFavorites AS F
WHERE U.idUser = '1'
AND U.idUser = F.idUserF;

