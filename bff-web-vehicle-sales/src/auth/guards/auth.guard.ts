import { HttpService } from '@nestjs/axios';
import {
  CanActivate,
  ExecutionContext,
  HttpException,
  HttpStatus,
  Injectable,
} from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { JwtService } from '@nestjs/jwt';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private jwtService: JwtService,
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}
  async canActivate(context: ExecutionContext): Promise<boolean> {
    const request = context.switchToHttp().getRequest();
    if (request.headers.authorization) {
      return await this.verifyToken(request);
    }
    throw new HttpException('Invalid Token', HttpStatus.UNAUTHORIZED);
  }

  private async verifyToken(request: any): Promise<boolean> {
    const tokenDecoded = this.jwtService.decode(
      request.headers.authorization.slice(7),
    ) as any;

    if (tokenDecoded === null) {
      throw new HttpException('Invalid Token', HttpStatus.UNAUTHORIZED);
    }
    if (await this.validateToken(request.headers.authorization.slice(7))) {
      return true;
    }
    throw new HttpException('Invalid Token', HttpStatus.UNAUTHORIZED);
  }

  private async validateToken(token: string): Promise<boolean> {
    const { status } = await firstValueFrom(
      this.httpService.request({
        method: 'GET',
        url: this.configService.get<string>('KEYCLOAK_USER_INFO_URL'),
        validateStatus: () => true,
        headers: { Authorization: `Bearer ${token}` },
      }),
    );
    return status === HttpStatus.OK;
  }
}
