


//1) saisir le nom du pokemon et valider avec le bouton
//inputName.value
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posX=0;
var posY=0;
//var rposY=100;
//var rposX=100;
//var rand=0;


var img="pixelCheffeBas";



btnStart.onclick=lancerAventure;

function lancerAventure()
{
  if(inputName.value!="")
  {
    formStart.style.display="none";
    grass.style.display="block";
    poule.setAttribute("title",inputName.value);
//    poule2.setAttribute("title2",inputName.value);
//    poule3.setAttribute("title3",inputName.value);
    document.body.onkeyup=deplacement;
  }
  else
  {
    error.innerHTML="Il faut saisir un nom !";
  }
}
function easy() {
    document.getElementById('poule1')
        .style.animationDuration = '100s';
    document.getElementById('poule1')
        .className = 'pixelpoules';
        document.getElementById('poule2')
            .style.animationDuration = '100s';
        document.getElementById('poule2')
            .className = 'pixelpoules';
            document.getElementById('poule3')
                .style.animationDuration = '100s';
            document.getElementById('poule3')
                .className = 'pixelpoules';
                document.getElementById('poule4')
                    .style.animationDuration = '250s';
                document.getElementById('poule4')
                    .className = 'pixelpoules';
                    document.getElementById('poule5')
                        .style.animationDuration = '150s';
                    document.getElementById('poule5')
                        .className = 'pixelpoules';
                        document.getElementById('poule6')
                            .style.animationDuration = '300s';
                        document.getElementById('poule6')
                            .className = 'pixelpoules';
                            document.getElementById('poule7')
                                .style.animationDuration = '200s';
                            document.getElementById('poule7')
                                .className = 'pixelpoules';
}
setInterval(easy, 1000)

function deplacement(event)
{
  if(event.key=="ArrowUp" || event.key=="z")
  {
    if(posY>=30)
    {
      posY-=30;
    }
    if(posY>=15 && event.ctrlKey)
    {
      posY-=15;
    }
    img="pixelCheffeHaut";
  }

  else if(event.key=="ArrowDown" || event.key=="s")
  {
    if(posY<=630)
    {
      posY+=30;
    }
    if(posY<=645 && event.ctrlKey)
    {
      posY+=15;
    }
    img="pixelCheffeBas";
  }

  else  if(event.key=="ArrowLeft" || event.key=="q")
  {
    if(posX>=30)
    {
      posX-=30;
      img="pixelCheffeGauche";
    }
    if(posX>=15 && event.ctrlKey)
    {
      posX-=15;
    }
  }

  else  if(event.key=="ArrowRight"|| event.key=="d")
  {
    if(posX<=630)
    {
      posX+=30;
    }
    if(posX<=645 && event.ctrlKey)
    {
      posX+=15;
    }
    img="pixelCheffeDroite";
  }




  poule.style.top=posY+"px";
  poule.style.left=posX+"px";
  imgPoule.setAttribute("src","assets/img/"+img+".png");

}
/* function random(){

  //var array = ['Cheffe','Coq','Insouciante','MamanPoule','Poussin','Psychopathe','Pyromane','Reine','Serieuse'];

  var allpoules=document.getElementsByClassName('Pixelpoules');

  for (var temp of allpoules){
    var img;
    rand = parseInt(Math.random() * 80) - 40;
    if (rand<=0 && rand>=-20){
      if(rposY>=30)
      {
        rposY-=30;
      }
      if(rposY>=15)
      {
        rposY-=15;
      }
      img="pixel"+temp.temperament+"Haut";
    }
    else if (rand<-20 && rand>=-40)
    {
      if(rposY<=630)
      {
        rposY+=30;
      }
      if(rposY<=645)
      {
        rposY+=15;
      }
      img="pixel"+temp.temperament+"Bas";
    }
    else if (rand<=20 && rand>0)
    {
      if(rposX>=30)
      {
        rposX-=30;
      }
      if(rposX>=15)
      {
        rposX-=15;
      }
      img="pixel"+temp.temperament+"Gauche";
    }
    else if (rand<=40 && rand>20){
      if(rposX<=630)
      {
        rposX+=30;
      }
      if(rposX<=645)
      {
        rposX+=15;
      }
      img="pixel"+temp.temperament+"Droite";
    }

    poule2.style.top=rposY+"px";
    poule2.style.left=rposX+"px";
    poule3.style.top=rposY+"px";
    poule3.style.left=rposX+"px";
  poule2.setAttribute("src","assets/img/"+img2+".png");
poule2.setAttribute("src","assets/img/"+img3+".png");
  }
}

setInterval(random, 1000)
*/
