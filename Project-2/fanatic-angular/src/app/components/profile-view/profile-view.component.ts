import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { NewUser } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { ReviewApiService } from 'src/app/services/review-api.service';

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.css']
})
export class ProfileViewComponent implements OnInit {
  user = new NewUser();
  score = 0;
  public reviewCount;

  constructor(
    public route: ActivatedRoute,
    public showUser: UserService,
    private loginService: LoginService,
    public reviewService: ReviewApiService
  ) {}

  ngOnInit() {
    this.findById();
    this.getMFScore(this.loginService.getUserID());
    this.getReviewCount(this.loginService.getUserID());
  }

  findById() {
   const id = this.loginService.getUserID();
   this.showUser.findById(id).subscribe(user => this.user = user);
  }

  /**
   * Requests the Movie Fanatics score for the user
   */
  getMFScore(user_id: number) {
    this.reviewService.getApprovals(user_id).subscribe(
      (approvals) => {
        let thumbs_up = 0;
        let thumbs_down = 0;

        // count the thumbs up and thumbs down
        approvals.forEach(element => {
          if (element.thumb === 1) {
            thumbs_up++;
          } else {
            thumbs_down++;
          }
        });

        // do the math
        if ((thumbs_down + thumbs_up) !== 0) {
          this.score = thumbs_up / (thumbs_down + thumbs_up);
        } else {
          this.score = 1;
        }
      }
    );
  }

  /**
   * Requests all of the reviews for the user and then counts them
   */
  getReviewCount(user_id: number) {
    this.reviewService.getUserReviews(user_id).subscribe (
      (reviews) => {
        this.reviewCount = reviews.length;
      }
    );
  }
}
