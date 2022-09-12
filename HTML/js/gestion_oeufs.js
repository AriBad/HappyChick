
function calculOeufs() {
    //calcul oeufs
    oeufs = totalOeuf.innerHTML
    oeufs -= (50 * nourritureAchat.value)
    if(securite.checked) {
        oeufs -= 20;
    }
    if (taille.checked) {
        oeufs -= 20;
    }

    //met à jour les input avec les oeufs restants
    nourritureAchat.max=oeufs%50

    if(oeufs<20 && !securite.checked) {
        securite.disabled=true;
    }
    else {
        securite.disabled=false;
    }

    if(oeufs<20 && !taille.checked) {
        taille.disabled=true;
    }
    else {
        taille.disabled=false;
    }

    oeufsDispos.innerHTML= oeufs;
}

nourritureAchat.oninput = calculOeufs;
securite.onchange = calculOeufs;
taille.onchange = calculOeufs;