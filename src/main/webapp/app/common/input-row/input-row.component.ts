import { KeyValuePipe } from '@angular/common';
import { Component, HostListener, inject, Input, OnChanges, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputErrorsComponent } from 'app/common/input-row/input-errors.component';


@Component({
  selector: 'app-input-row',
  templateUrl: './input-row.component.html',
  imports: [ReactiveFormsModule, InputErrorsComponent, KeyValuePipe]
})
export class InputRowComponent implements OnChanges, OnInit {

  @Input({ required: true })
  group?: FormGroup;

  @Input({ required: true })
  field = '';

  @Input()
  rowType = 'text';

  @Input()
  inputClass = '';

  @Input()
  options?: Record<string, string>|Map<number, string>;

  @Input({ required: true })
  label = '';

  control?: AbstractControl;
  optionsMap?: Map<string|number,string>;

  ngOnInit() {
    this.control = this.group!.get(this.field)!;
  }

  ngOnChanges() {
    if (!this.options || this.options instanceof Map) {
      this.optionsMap = this.options;
    } else {
      this.optionsMap = new Map(Object.entries(this.options));
    }
  }

  @HostListener('input', ['$event.target'])
  onEvent(target: HTMLInputElement) {
    if (target.value === '') {
      this.control!.setValue(null);
    }

    if (this.rowType === 'number') {
      const value = target.valueAsNumber;
      this.control!.setValue(isNaN(value) ? null : value);
    } else {
      this.control!.setValue(target.value);
    }
  }

  isRequired() {
    return this.control?.hasValidator(Validators.required);
  }

  getInputClasses() {
    return (this.hasErrors() ? 'border-red-600 ' : '') + (this.control?.disabled ? 'bg-gray-100 ' : '') + this.inputClass;
  }

  hasErrors() {
    return this.control?.invalid && (this.control?.dirty || this.control?.touched);
  }

}
