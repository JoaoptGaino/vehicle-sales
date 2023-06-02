import { Module } from '@nestjs/common';
import { AuthService } from './services/impl/auth.service.impl';
import { AuthController } from './controllers/auth.controller';
import { HttpModule } from '@nestjs/axios';
import { JwtModule } from '@nestjs/jwt';
import { AUTH_SERVICE } from './constants/auth.service.constant';

@Module({
  imports: [HttpModule, JwtModule],
  providers: [{ provide: AUTH_SERVICE, useClass: AuthService }],
  controllers: [AuthController],
})
export class AuthModule {}
