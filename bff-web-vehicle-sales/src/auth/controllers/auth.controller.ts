import {
  Body,
  Controller,
  Get,
  Inject,
  Post,
  Request,
  UseGuards,
} from '@nestjs/common';
import { AUTH_SERVICE } from '../constants/auth.service.constant';
import { UserLoginFormDTO } from '../dto/user.login.form.dto';
import { AuthGuard } from '../guards/auth.guard';
import { IAuthService } from '../services/auth.service';

@Controller('auth')
export class AuthController {
  constructor(
    @Inject(AUTH_SERVICE) private readonly authService: IAuthService,
  ) {}

  @Post('login')
  login(@Body() userData: UserLoginFormDTO) {
    return this.authService.login(userData);
  }

  @Get('profile')
  @UseGuards(AuthGuard)
  profile(@Request() req) {
    return this.authService.profile(req.headers.authorization);
  }
}
