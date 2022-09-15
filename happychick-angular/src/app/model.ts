
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
    utilisateur:Utilisateur

    constructor(nom:string, utilisateur: Utilisateur, id?: number, securite?: number, taille?: number, nourriture?: number, oeufs?: number,annee?: number,saison? :string) {
        this.nom=nom;
        this.utilisateur = utilisateur;
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
    listeCouveuses:Array<Couveuse>;

    constructor(nourriture?:number, securite?:boolean, taille?:boolean, activite?:string, couveuses?:Array<Couveuse>) {
        this.nourriture=nourriture;
        this.securite=securite;
        this.taille=taille;
        this.activite=activite;
        this.listeCouveuses=couveuses;
    }

}

export class Couveuse {
    // idPoule:number;
    // oeufs:number;
    // constructor(idPoule:number, oeufs:number) {
    //     this.idPoule=idPoule;
    //     this.oeufs=oeufs;
    // }
    poule:Poule;
    oeufs:number;
    constructor(poule:Poule, oeufs:number) {
        this.poule=poule;
        this.oeufs=oeufs;
    }
}

export class CouveuseComplete {
    poule:Poule;
    oeufs:number;
    constructor(poule:Poule, oeufs:number) {
        this.poule=poule;
        this.oeufs=oeufs;
    }
}

export class Utilisateur {
    id: number;
    version: number;
    login: string;
    motDePasse: string;
    activated: boolean;
    roles: Set<string> = new Set<string>();

    constructor(id?: number, version?: number, login? : string, motDePasse?: string, activated?: boolean) {
        this.id = id;
        this.version = version;
        this.login = login;
        this.motDePasse = motDePasse;
        this.activated = activated;
    }
}

export class Recap {
    poulailler:Poulailler;
    saison:number;
    recapCourt: string;
    recapLong: string;
    constructor(poulailler:Poulailler, saison:number, recapCourt: string, recapLong: string) {
        this.poulailler=poulailler;
        this.saison=saison;
        this.recapCourt=recapCourt;
        this.recapLong=recapLong;
    }
}