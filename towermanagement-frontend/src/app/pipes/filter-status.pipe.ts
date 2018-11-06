import { Pipe, PipeTransform } from '@angular/core';
import { Complaint } from '../model/complaint';

@Pipe({
  name: 'filterStatus'
})
export class FilterStatusPipe implements PipeTransform {

  transform(complaints:Complaint[], actionStatus:string): Complaint[] {
    if(!complaints) return [];
    if(!actionStatus) return complaints;
    return complaints.filter( c => {
      return c.actionStatus==actionStatus;
    });
  }

}