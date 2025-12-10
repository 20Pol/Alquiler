import { Component, AfterViewInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import AOS from 'aos';
import 'aos/dist/aos.css';
import fullpage from 'fullpage.js';
import 'fullpage.js/dist/fullpage.min.css';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements AfterViewInit, OnDestroy {

  // referencia a la instancia de fullpage
  private fpInstance: any;

  vehiculosDestacados = [
    { nombre: 'Gama Alta', imagen: '/images/destacado1.jpg' },
    { nombre: 'Sedán Ejecutivo', imagen: '/images/ejem2.jpg' },
    { nombre: 'SUV Familiar', imagen: '/images/ejem3.jpg' },
    { nombre: 'Camioneta 4x4', imagen: '/images/ejem4.jpg' }
  ];

servicios = [
  { icono: 'fa-shield-halved', titulo: 'Seguro Completo', descripcion: 'Protección total durante tu recorrido.' },
  { icono: 'fa-map-location-dot', titulo: 'GPS Integrado', descripcion: 'Guías precisas para llegar sin problemas.' },
  { icono: 'fa-gas-pump', titulo: 'Combustible Incluido', descripcion: 'Opciones de tanque lleno.' },
  { icono: 'fa-headset', titulo: 'Soporte 24/7', descripcion: 'Ayuda inmediata cuando lo necesites.' }

];



  ngAfterViewInit(): void {
    // Inicializa AOS
    AOS.init({
      duration: 900,
      easing: 'ease-in-out',
      once: true
    });

    // Inicializa fullpage.js
    this.fpInstance = new fullpage('#fullpage', {
      // opciones útiles
      licenseKey: '', // si tienes licencia, colócala. Para uso personal local puedes dejar vacío.
      autoScrolling: true,
      verticalCentered: true,
      navigation: true,           // puntos de navegación a la derecha
      navigationPosition: 'right',
      showActiveTooltip: false,
      scrollOverflow: true,       // permite scroll dentro de secciones si el contenido pasa
      responsiveWidth: 768,       // desactiva autoScrolling en pantallas < 768
      // callbacks para integrar AOS
      afterLoad: (origin: any, destination: any, direction: any) => {
        // refresca AOS para que anime elementos cuando la sección se muestra
        AOS.refresh();
      },
      onLeave: (origin: any, destination: any, direction: any) => {
        // opcional: hacer algo al salir
      }
    });

    const options = {
    root: null,
    threshold: 0.6
  };

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('active');
      } else {
        entry.target.classList.remove('active');
      }
    });
  }, options);

  document.querySelectorAll('.vehicle-item').forEach(item => {
    observer.observe(item);
  });
  }

  ngOnDestroy(): void {
    // destruir la instancia para evitar fugas de memoria
    try {
      if (this.fpInstance && typeof this.fpInstance.destroy === 'function') {
        this.fpInstance.destroy('all');
      }
    } catch (e) {
      // no crítico si falla
      console.warn('Error al destruir fullpage instance', e);
    }
  }

 
}
