import { Component, OnInit } from '@angular/core';
import { NewUser } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';




@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  user =  new NewUser();

  constructor(private router: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    this.user.is_admin = 0;
  }

  submitNewUser() {
    this.userService.createUser(this.user)
    .subscribe(data => {
      alert('User Created.');
    });
  }
}
