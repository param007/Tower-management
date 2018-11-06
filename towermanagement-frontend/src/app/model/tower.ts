import { CSA } from "./csa";
import { Complaint } from "./complaint";
import { Company } from "./company";

export class Tower {
    public towerId:number;
    public circle:string;
    public address:string;
    public engineerAssc:string;
    public lease:string;
    public latitude:number;
    public longitude:number;
    public companies:Company[];
    public software:string;
    public hardware:string;
    public status:boolean;
    public csa:CSA;
    public complaints:Complaint[];

}
