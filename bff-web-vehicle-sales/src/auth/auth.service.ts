import { Injectable } from '@nestjs/common';
import { UserLoginFormDTO } from './dto/user.login.form.dto';
import { HttpService } from '@nestjs/axios';
import { ConfigService } from '@nestjs/config';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class AuthService {
  constructor(
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}
  async login(userData: UserLoginFormDTO) {
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

    const response = await firstValueFrom(
      this.httpService.request({
        method: 'POST',
        url: this.configService.get<string>('KEYCLOAK_TOKEN_URL'),
        data,
      }),
    );
    return response.data;
  }
}
