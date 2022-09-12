
export class Poule {
    id: number;
    prenom: string;
    age: number;
    bonheur: number;
    ponte: number;
    maternage: number;
    predation: number;
    maladie: number;
    poussin: boolean;
    femelle: boolean;
    temperament: Set<string> = new Set<string>();;
    poulailler: Poulailler;
    oeufsCouves: number;
    etat: Set<string> = new Set<string>();;
    saisonSansManger: number;
    causeMort: Set<string> = new Set<string>();;


    constructor(id?: number, prenom?: string, femelle?: Boolean, poulailler?: Poulailler) {
        this.id = id;
        this.age = 0;
        this.prenom = prenom;
        this.poussin = true;
		this.femelle = true;
		this.poulailler = poulailler;
		this.etat=Etat.Liberte;
		this.saisonSansManger = 0;	

    }
}
export class Poulailler {
    id: number;
    securite: number;
    taille: number;
    nourriture: number;
    oeufs: number;
    annee: number;
    saison: Set<string> = new Set<string>();;
    nbMort: number = 0;
    nom: string;
    aciveSaison: Set<string> = new Set<string>();;
    listePoules: Array<Poule> = new Array<Poule>();

    constructor(id?: number, securite?: number, taille?: number, nourriture?: number, oeufs?: number,annee?: number,saison?: Set<string>) {
        this.id = id;
        this.nom = "TODO";
        this.securite = securite;
		this.taille = taille;
		this.nourriture = nourriture;
		this.oeufs = oeufs;
		this.annee = annee;
		this.saison = saison;
    }
}