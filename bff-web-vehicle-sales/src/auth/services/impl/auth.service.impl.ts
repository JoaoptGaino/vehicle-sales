import { Injectable } from '@nestjs/common';
import { UserLoginFormDTO } from '../../dto/user.login.form.dto';
import { HttpService } from '@nestjs/axios';
import { ConfigService } from '@nestjs/config';
import { firstValueFrom } from 'rxjs';
import { IAuthService } from '../auth.service';
import { LoginDTO } from 'src/auth/dto/login.dto';
import { ProfileDTO } from 'src/auth/dto/profile.dto';
import { plainToInstance } from 'class-transformer';

@Injectable()
export class AuthService implements IAuthService {
  constructor(
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}
  async login(userData: UserLoginFormDTO): Promise<LoginDTO> {
    const data = this.getSearchParams(userData);

    const response = await firstValueFrom(
      this.httpService.request({
        method: 'POST',
        url: this.configService.get<string>('KEYCLOAK_TOKEN_URL'),
        data,
      }),
    );
    return plainToInstance(LoginDTO, response.data);
  }

  async profile(token: string): Promise<ProfileDTO> {
    const response = await firstValueFrom(
      this.httpService.request({
        method: 'GET',
        url: this.configService.get<string>('KEYCLOAK_USER_INFO_URL'),
        headers: {
          Authorization: token,
        },
      }),
    );
    return plainToInstance(ProfileDTO, response.data);
  }

  private getSearchParams(userData: UserLoginFormDTO) {
    const data = new URLSearchParams();
    data.append('grant_type', 'password');
    data.append(
      'client_id',
      this.configService.get<string>('KEYCLOAK_CLIENT_ID'),
    );
    data.append(
      'client_secret',
      this.configService.get<string>('KEYCLOAK_CLIENT_SECRET'),
    );
    data.append('username', userData.username);
    data.append('password', userData.password);
    return data;
  }
}
