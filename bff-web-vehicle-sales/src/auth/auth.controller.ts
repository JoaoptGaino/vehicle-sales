import { Controller, Post, Body, Get, UseGuards } from '@nestjs/common';
import { AuthService } from './auth.service';
import { UserLoginFormDTO } from './dto/user.login.form.dto';
import { AuthGuard } from './guards/auth.guard';

@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('login')
  login(@Body() userData: UserLoginFormDTO) {
    return this.authService.login(userData);
  }

  @Get('test')
  @UseGuards(AuthGuard)
  test() {
    return 'test';
  }
}
