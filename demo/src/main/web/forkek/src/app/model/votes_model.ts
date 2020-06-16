export class votes{
    
    upvotes: number;
    downvotes: number;
    upvoters: String[];
    downvoters: String[];

    constructor(uv:number,dv:number,uvs: String[],dvs: String[]) {
        this.upvotes=uv;
        this.downvotes=dv;
        this.upvoters=uvs;
        this.downvoters=dvs;
    }
}