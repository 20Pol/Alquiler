import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../servicios/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-dashboard-layout',
  standalone: true,                 // 👈 IMPORTANTE
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.css'],
  imports: [RouterModule, FormsModule, CommonModule]           // 👈 NECESARIO para routerLink y router-outlet
})
export class DashboardLayoutComponent {

  constructor(public authService: AuthService, private router: Router) {}

    logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
