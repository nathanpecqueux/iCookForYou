SELECT U.nom, U.prenom, A.idTAllergie, A.nom 
FROM TUser AS U, TAllergie AS A, TUserAllergie AS UA
WHERE U.idTUser = '1' 
AND U.idTUser = UA.idTUserUA 
AND UA.idTAllergieUA = A.idTAllergie


SELECT U.nom, U.prenom, ALI.nom, ALI.quantite, ALI.unite, S.stock
FROM TUser AS U, TAliment AS ALI, TStock AS S
WHERE U.idTUser = '0'
AND U.idTUser = S.idUserS
AND S.idAliment = ALI.idAliment


INSERT INTO `TAllergie` (`idTAllergie`, `nom`) VALUES
(6, 'poissons');

INSERT INTO `TUserAllergie` (`idTUserUA`, `idTAllergieUA`) VALUES
(0, 6);

INSERT INTO `TUser` (`idTUser`, `nom`, `prenom`, `mail`, `password`) VALUES
(1, 'pecqueux', 'nathan', 'nathan@gmail.com', 'nathan');

INSERT INTO `TUserAllergie` (`idTUserUA`, `idTAllergieUA`) VALUES
(1, 6);