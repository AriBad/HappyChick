<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>HappyChick - Detail des poules</title>
	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
</head>
<body>
	<div id="header">
		<!-- 
		<img src="img/header.png" width=100% height=100%>
		 -->
	</div>
	<div id="center">
		<div id="main">
			<div id="PouleList">
				<h1>Details des poules</h1>
				<div id="menuAffichagePoules">
					<table>
						<td>
							Poules � afficher : 
						</td>
						<br>
						<td>
							<select name="affichage">
								<option value="Toutes">Toutes</option>
								<option value="mortes">Mortes</option>
								<option value="Vivantes">Vivantes</option>
								<option value="Serieuses">S�rieuses</option>
								<option value="Insouciantes">Insouciantes</option>
								<option value="MamanPoules">Mamans Poules</option>
								<option value="Psychopathes">Psychopathes</option>
								<option value="Pyromanes">Pyromanes</option>
								<option value="Poussins">Poussins</option>
							</select>
						</td>
						<td>
							Tri : 
						</td>
						<td>
							<select name="affichage">
								<option value="id">Par Id</option>
								<option value="AgeAsc">Par Age croissant</option>
								<option value="AgeDesc">Par Age Descroissant</option>
								<option value="PrenomAlph">par leur pr�nom</option>
							</select>
						</td>
						<td>
							<button type="button" id="afficherCouveuses" class="btnViolet">Afficher les poules selection�es</button>
						</td>
					</table>
					<br>
				</div>
				<div id="poulesIdentite">
					<div class="fiche">
						<div>
							<img src="img/PouleInsouciante.png" width="auto" height=150>
						</div>
						<div class="informations">
							<strong>Marie-Catherine </strong>
							<br>
							<strong>Temp�rament :</strong>
							 Insouciante 
							<br>
							<strong>Age :</strong>
							 2,75 ans 
							<br>
							<div id="bonheur">
								<strong>Bonheur </strong>
								 (25 %)
								<div class="progress">
									<div class="progress-bar progress-bar-striped bg-infos" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</div>
							<strong>Etat :</strong>
							 Libert� 
							<br>
						</div>
                        <div class="action">
                            <button type="button" class="btn btn-primary">Mettre � couver</button></td> <td><button type="button" id="afficherCouveuses" class="btn btn-warning">Envoyer en voyage (d�finitif)</button>
                        </div>
					</div>
					<div class="fiche">
						<div>
							<img src="img/PouleSerieuse.png" width="auto" height=150>
						</div>
						<div class="informations">
							<strong>Agn�s </strong>
							<br>
							<strong>Temp�rament :</strong>
							 Serieuse 
							<br>
							<strong>Age :</strong>
							 7 ans 
							<br>
							<div id="bonheur">
								<strong>Bonheur </strong>
								 (85 %)
								<div class="progress">
									<div class="progress-bar progress-bar-striped bg-infos" role="progressbar" style="width: 85%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</div>
							<strong>Etat :</strong>
							 Maternage 
							<br>
						</div>
                        <div class="action">
                            <button type="button" class="btn btn-primary">Mettre � couver</button></td> <td><button type="button" id="afficherCouveuses" class="btn btn-warning">Envoyer en voyage (d�finitif)</button>
                        </div>
					</div>
					<div class="fiche">
						<div>
							<img src="img/PoulePsychopathe.png" width="auto" height=150>
						</div>
						<div class="informations">
							<strong>Alisson </strong>
							<br>
							<strong>Temp�rament :</strong>
							 Psychopathe 
							<br>
							<strong>Age :</strong>
							 0.25 ans 
							<br>
							<div id="bonheur">
								<strong>Bonheur </strong>
								 (3 %)
								<div class="progress">
									<div class="progress-bar progress-bar-striped bg-infos" role="progressbar" style="width: 3%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</div>
							<strong>Etat :</strong>
							 Couvaison (7 oeufs)
							<br>
						</div>
                        <div class="action">
                            <button type="button" class="btn btn-primary">Mettre � couver</button></td> <td><button type="button" id="afficherCouveuses" class="btn btn-warning">Envoyer en voyage (d�finitif)</button>
                        </div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div id="footer">
		<!-- 
		<img src="img/footer.png" width=100% height=100%>
		 -->
	</div>
</body>
<script type="text/javascript" src="js/gestion_oeufs.js"></script>
<script>
	afficherCouveuses.onclick=afficherCouv;
	function afficherCouv(){
		// couveusestable.style.display="contents"
		if(couveusestable.style.display=="none"){
			couveusestable.style.display="contents";
			afficherCouveuses.innerHTML="Cacher les poules selection�es";
		}
		 else{
			couveusestable.style.display="none";
			afficherCouveuses.innerHTML="Afficher les poules selection�es";
		}
	}
</script>


</html>