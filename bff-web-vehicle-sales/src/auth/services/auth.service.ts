import { LoginDTO } from '../dto/login.dto';
import { ProfileDTO } from '../dto/profile.dto';
import { UserLoginFormDTO } from '../dto/user.login.form.dto';

export interface IAuthService {
  login(userData: UserLoginFormDTO): Promise<LoginDTO>;
  profile(token: string): Promise<ProfileDTO>;
}
