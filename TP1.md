

TP 1

1. Créez les classes entities ci-contre (Preview  avant-première du film)
   Correspondance des types DB=>Java: varchar(255) => String, real => Float, bigint => Long
2. Créez une base de données avec pgAdmin nommée movie_db
3. Configurez le fichier persistence.xml afin qu’il communique avec movie_db
4. Lancez la méthode main de la classe com.freestack.persistence.Initializer.java
5. Vérifiez que la base de données a été mise à jourFaites une requête permettant de récupérer des films par la présence d’un mot dans leur titre ou dans leur description, afficher le résultat