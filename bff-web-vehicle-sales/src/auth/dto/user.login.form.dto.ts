import { IsNotEmpty, IsString } from 'class-validator';

export class UserLoginFormDTO {
  @IsNotEmpty({ message: 'Username is required' })
  @IsString({ message: 'Username must be a string' })
  username: string;

  @IsNotEmpty({ message: 'Password is required' })
  @IsString({ message: 'Password must be a string' })
  password: string;

  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}
