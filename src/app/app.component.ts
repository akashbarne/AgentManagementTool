import { Component, Inject, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';

const baseUrl = 'http://10.80.11.18:4000'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  postData = {
    agentIP: '',
    userName: '',
    password: '',
    agentVersion: '',
    agentType: '',
    consoleIP: '',
    passphrase: '',
    port: ''
  }

  displayedColumns: string[] = ['agentIP', 'agentType', 'agentVersion', 'consoleIP', 'agentStatus'];
  dataSource = new MatTableDataSource([]);

  constructor(public dialog: MatDialog,
    private http: HttpClient) { }

  ngOnInit() {
    this.fetchAgents();
  }

  fetchAgents() {
    this.http.get(`${baseUrl}/getAgent`).subscribe((res: any) => {
      console.log(res);
      this.dataSource = new MatTableDataSource(res);
    }, err => console.log(err));
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      data: this.postData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.installAgent(result, () => {
          this.fetchAgents();
        })
      }
      this.postData = {
        agentIP: '',
        userName: '',
        password: '',
        agentVersion: '',
        agentType: '',
        consoleIP: '',
        passphrase: '',
        port: ''
      }
    });
  }

  installAgent(params, callback) {
    this.http.post(`${baseUrl}/installAgent`, params).subscribe(res => {
      callback();
    }, err => console.log(err));
  }
}

@Component({
  selector: 'new-agent-dialog',
  templateUrl: 'new-agent-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}