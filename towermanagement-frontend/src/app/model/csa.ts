import { Tower } from "./tower";
import { Complaint } from "./complaint";

export class CSA {
    public csaId:number;
	public circle:string;
	public name:string;
	public email:string;
	public towers:Tower[];
	public complaints:Complaint[];
}
