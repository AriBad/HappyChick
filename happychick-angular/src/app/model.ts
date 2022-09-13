
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
    temperament: string;
    poulailler: Poulailler;
    oeufsCouves: number;
    etat: string;
    saisonSansManger: number;
    causeMort: string;


    constructor(id?: number, prenom?: string, femelle?: Boolean, poulailler?: Poulailler) {
        this.id = id;
        this.age = 0;
        this.prenom = prenom;
        this.poussin = true;
		this.femelle = true;
		this.poulailler = poulailler;
		// this.etat=Etat.Liberte;
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
    saison: string;
    nbMort: number = 0;
    nom: string;
    activiteSaison: string;
    listePoules: Array<Poule> = new Array<Poule>();

    constructor(nom:string, id?: number, securite?: number, taille?: number, nourriture?: number, oeufs?: number,annee?: number,saison? :string) {
        this.nom=nom;
        this.id = id;
        this.securite = securite;
		this.taille = taille;
		this.nourriture = nourriture;
		this.oeufs = oeufs;
		this.annee = annee;
		this.saison = saison;
    }
}

export class Saison {
    nourriture:number;
    securite:boolean;
    taille:boolean;
    activite:string;
    couveuses:Array<Couveuse>;

    constructor(nourriture?:number, securite?:boolean, taille?:boolean, activite?:string, couveuses?:Array<Couveuse>) {
        this.nourriture=nourriture;
        this.securite=securite;
        this.taille=taille;
        this.activite=activite;
        this.couveuses=couveuses;
    }

}

export class Couveuse {
    idPoule:number;
    nbOeufs:number;
    constructor(idPoule:number, nbOeufs:number) {
        this.idPoule=idPoule;
        this.nbOeufs=nbOeufs;
    }
}