import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { VehicleCatalogModule } from './vehicle-catalog/vehicle-catalog.module';
import { ClientModule } from './clients/client.module';
import { AuthModule } from './auth/auth.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      envFilePath: '.env',
      isGlobal: true,
    }),
    VehicleCatalogModule,
    ClientModule,
    AuthModule,
  ],
})
export class AppModule {}
