
function calculOeufs() {
    oeufs = totalOeuf.innerHTML
    oeufs -= (40 * nourritureAchat.value)
    if(securite.checked) {
        oeufs -= 20;
    }
    if (taille.checked) {
        oeufs -= 20;
    }
    oeufsDispos.innerHTML= oeufs;
}

nourritureAchat.oninput = calculOeufs;
securite.onchange = calculOeufs;
taille.onchange = calculOeufs;