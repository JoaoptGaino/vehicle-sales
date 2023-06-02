import { Expose } from 'class-transformer';

export class LoginDTO {
  @Expose()
  access_token: string;

  @Expose()
  expires_in: number;

  @Expose()
  refresh_expires_in: number;

  @Expose()
  refresh_token: string;

  @Expose()
  token_type: string;

  @Expose()
  not_before_policy: number;

  @Expose()
  session_state: string;

  @Expose()
  scope: string;

  constructor(
    access_token: string,
    expires_in: number,
    refresh_expires_in: number,
    refresh_token: string,
    token_type: string,
    not_before_policy: number,
    session_state: string,
    scope: string,
  ) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.refresh_expires_in = refresh_expires_in;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
    this.not_before_policy = not_before_policy;
    this.session_state = session_state;
    this.scope = scope;
  }
}
