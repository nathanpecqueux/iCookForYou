
#Liste des aliments avec leurs quantités dans le stock de l'user 1
SELECT U.name, U.firstname, F.idFood, F.name, F.quantity, UNI.name, S.quantityS
FROM TUser AS U, TFood AS F, TStock AS S, TUnit AS UNI
WHERE U.idUser = '1'
AND U.idUser = S.idUserS
AND S.idFood = F.idFood
AND F.idUnitF = UNI.idUnit;

#Quantité totale d'un aliment X pour l'utilisateur 1
SELECT U.name, U.firstname, F.name, (F.quantity*S.quantityS) AS QuantityTotal, UNI.name
FROM TUser AS U, TFood AS F, TStock AS S, TUnit AS UNI
WHERE U.idUser = '1'
AND S.idFood = F.idFood
AND F.name = "lait"
AND F.idUnitF = UNI.idUnit;

#Quantité totale des aliments dans le stock de l'utilisateur 1
SELECT U.name, U.firstname, F.name, (F.quantity*S.quantityS) AS QuantityTotal, UNI.name
FROM TUser AS U, TFood AS F, TStock AS S, TUnit AS UNI
WHERE U.idUser = '1'
AND U.idUser = S.idUserS
AND S.idFood = F.idFood
AND F.idUnitF = UNI.idUnit;

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

#Afficher les aliments qui correspond à la catégorie 1
SELECT F.name, F.idCategoryF
FROM TFood AS F, TCategory AS C
WHERE F.idCategoryF = 1
AND F.idCategoryF = C.idCategory;

#Afficher les sous categories de la categorie 1
SELECT C.name 
FROM TCategory AS C
WHERE idCategory = '1'
UNION 
SELECT name 
FROM (SELECT * FROM TCategory AS C ORDER BY idSubCategory, idCategory) categories,
	 (SELECT @ct := '1') initialisation
	  WHERE find_in_set(idSubCategory, @ct)>0
	  AND @ct := concat(@ct, ',', idCategory);


#Afficher toutes les categories des aliments du stock de l'utilisateur 1
SELECT U.name AS UserName, C.name AS CategoryName
FROM TCategory AS C, TUser AS U, TFood AS F, TStock AS S
WHERE U.idUser = '1'
AND U.idUser = S.idUserS
AND S.idFood = F.idFood
AND F.idCategoryF = C.idCategory;





