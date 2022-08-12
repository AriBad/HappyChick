var topY=300;
var leftX=600;

function bougerPoule() {
    pouleMouvante.style.top = topY +'px';
    pouleMouvante.style.left = leftX + 'px';
    pouleMouvante.style.zIndex="1";
    xchange = parseInt(Math.random() * 100) - 50; //random entre -50 et 50
    ychange = parseInt(Math.random() * 100) - 50;
    topY+= ychange;
    leftX+= xchange;

}

setInterval(bougerPoule, 500)