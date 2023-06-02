import { Expose } from 'class-transformer';

export class ProfileDTO {
  @Expose()
  sub: string;

  @Expose()
  email_verified: boolean;

  @Expose()
  preferred_username: string;

  @Expose()
  given_name: string;

  @Expose()
  family_name: string;

  @Expose()
  email: string;

  constructor(
    sub: string,
    email_verified: boolean,
    preferred_username: string,
    given_name: string,
    family_name: string,
    email: string,
  ) {
    this.sub = sub;
    this.email_verified = email_verified;
    this.preferred_username = preferred_username;
    this.given_name = given_name;
    this.family_name = family_name;
    this.email = email;
  }
}
